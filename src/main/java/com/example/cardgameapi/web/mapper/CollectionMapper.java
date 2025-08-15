package com.example.cardgameapi.web.mapper;

import com.example.cardgameapi.entity.CollectionItem;
import com.example.cardgameapi.entity.InventoryItem;
import com.example.cardgameapi.web.response.CollectionItemResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollectionMapper {

    public List<CollectionItemResponse> toCollectionItemResponseList(List<CollectionItem> collectionItems) {
        return collectionItems.stream().map(this::toCollectionItemResponse).toList();
    }

    public CollectionItemResponse toCollectionItemResponse(CollectionItem collectionItem) {
        CollectionItemResponse collectionItemResponse = new CollectionItemResponse();
        collectionItemResponse.setId(collectionItem.getId());
        collectionItemResponse.setName(collectionItem.getName());
        collectionItemResponse.setImage(collectionItem.getImage());
        collectionItemResponse.setCreatedAt(collectionItem.getCreatedAt().toString());
        collectionItemResponse.setUpdatedAt(collectionItem.getUpdatedAt().toString());
        return collectionItemResponse;
    }

}
