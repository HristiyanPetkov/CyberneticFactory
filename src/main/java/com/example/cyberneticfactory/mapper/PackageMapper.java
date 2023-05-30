package com.example.cyberneticfactory.mapper;

import com.example.cyberneticfactory.controller.resources.PackageResource;
import com.example.cyberneticfactory.entity.Package;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ProductMapper.class})
public interface PackageMapper {

    PackageMapper PACKAGE_MAPPER = Mappers.getMapper(PackageMapper.class);

    Package fromPackageResource(PackageResource packageResource);

    PackageResource toPackageResource(Package aPackage);

    List<PackageResource> toPackageResources(List<Package> packages);
}
