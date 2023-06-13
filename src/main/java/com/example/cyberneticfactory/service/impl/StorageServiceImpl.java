package com.example.cyberneticfactory.service.impl;

import com.example.cyberneticfactory.controller.resources.MaterialResource;
import com.example.cyberneticfactory.controller.resources.StorageResource;
import com.example.cyberneticfactory.entity.Material;
import com.example.cyberneticfactory.entity.Storage;
import com.example.cyberneticfactory.repository.MaterialRepository;
import com.example.cyberneticfactory.repository.StorageRepository;
import com.example.cyberneticfactory.service.StorageService;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cyberneticfactory.mapper.StorageMapper.STORAGE_MAPPER;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;
    private final MaterialRepository materialRepository;
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public List<StorageResource> getAll() {
        return STORAGE_MAPPER.toStorageResources(storageRepository.findAll());
    }

    @Override
    public StorageResource getById(Long id) {
        return STORAGE_MAPPER.toStorageResource(storageRepository.getReferenceById(id));
    }

    @Override
    public StorageResource save(StorageResource storage) {
        Storage storageToSave = STORAGE_MAPPER.fromStorageResource(storage);
        storageToSave.setMaterials(materialRepository.findAllById(
                storage.getMaterials().stream().map(MaterialResource::getId).toList()));

        return STORAGE_MAPPER.toStorageResource(storageRepository.save(storageToSave));
    }

    @Override
    public StorageResource update(StorageResource storage, long id) {
        Storage storageToUpdate = storageRepository.getReferenceById(id);
        storageToUpdate.setName(storage.getName());
        storageToUpdate.setCapacity(storage.getCapacity());
        storageToUpdate.setUsed(storage.getUsed());
        storageToUpdate.setMaterials(materialRepository.findAllById(
                storage.getMaterials().stream().map(MaterialResource::getId).toList()));

        return STORAGE_MAPPER.toStorageResource(storageRepository.save(storageToUpdate));
    }

    @Override
    public void delete(Long id) {
        materialRepository.findAllByStorages_Id(id).forEach(material -> removeStorageFromMaterial(material, id));
        storageRepository.deleteById(id);
    }

    @Override
    public Object getAudits() {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        return auditReader.createQuery()
                .forRevisionsOfEntity(Storage.class, true, true)
                .getResultList();
    }

    private void removeStorageFromMaterial(Material material, Long id) {
        material.getStorages().removeIf(storage -> storage.getId().equals(id));
        materialRepository.save(material);
    }
}
