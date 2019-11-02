package com.ggp.msproducts.repository;

import com.ggp.msproducts.model.Catalog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "catalogs", path = "catalogs")
public interface CatalogRepository extends PagingAndSortingRepository<Catalog, Long> {
}
