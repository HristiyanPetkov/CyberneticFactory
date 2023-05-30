package com.example.cyberneticfactory.service.impl;

import com.example.cyberneticfactory.controller.resources.MaterialResource;
import com.example.cyberneticfactory.entity.Material;
import com.example.cyberneticfactory.entity.Part;
import com.example.cyberneticfactory.entity.Storage;
import com.example.cyberneticfactory.repository.MaterialRepository;
import com.example.cyberneticfactory.repository.PartRepository;
import com.example.cyberneticfactory.repository.StorageRepository;
import com.example.cyberneticfactory.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cyberneticfactory.mapper.MaterialMapper.MATERIAL_MAPPER;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final StorageRepository storageRepository;
    private final PartRepository partRepository;

    @Override
    public List<MaterialResource> getAll() {
        return MATERIAL_MAPPER.toMaterialResources(materialRepository.findAll());
    }

    @Override
    public MaterialResource getById(Long id) {
        return MATERIAL_MAPPER.toMaterialResource(materialRepository.getReferenceById(id));
    }

    @Override
    public MaterialResource save(MaterialResource material) {
        Material materialToSave = MATERIAL_MAPPER.fromMaterialResource(material);
        materialToSave.setStorages(null);
        materialToSave.setParts(null);

        return MATERIAL_MAPPER.toMaterialResource(materialRepository.save(materialToSave));
    }

    @Override
    public MaterialResource update(MaterialResource material, long id) {
        Material materialToUpdate = materialRepository.getReferenceById(id);
        materialToUpdate.setType(material.getType());

        return MATERIAL_MAPPER.toMaterialResource(materialRepository.save(materialToUpdate));
    }

    @Override
    public void delete(Long id) {
        storageRepository.findAllByMaterials_Id(id).forEach(storage -> removeMaterialFromStorage(storage, id));
        partRepository.findAllByMaterials_Id(id).forEach(part -> removeMaterialFromPart(part, id));
        materialRepository.deleteById(id);
    }

    private void removeMaterialFromPart(Part part, long id) {
        part.getMaterials().removeIf(material -> material.getId().equals(id));
        partRepository.save(part);
    }

    private void removeMaterialFromStorage(Storage storage, long id) {
        storage.getMaterials().removeIf(material -> material.getId().equals(id));
        storageRepository.save(storage);
    }
}
