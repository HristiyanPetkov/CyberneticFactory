package com.example.cyberneticfactory.mapper;

import com.example.cyberneticfactory.controller.resources.StorageResource;
import com.example.cyberneticfactory.entity.Storage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {MaterialMapper.class})
public interface StorageMapper {

    StorageMapper STORAGE_MAPPER = Mappers.getMapper(StorageMapper.class);

    Storage fromStorageResource(StorageResource storageResource);

    StorageResource toStorageResource(Storage storage);

    List<StorageResource> toStorageResources(List<Storage> storages);
}
