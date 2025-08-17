package com.example.cardgameapi.web.mapper;

import com.example.cardgameapi.entity.collection.Character;
import com.example.cardgameapi.web.response.CollectionItemResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CollectionMapper {

    public List<CollectionItemResponse> toCollectionItemResponseList(Set<Character> collectionItems) {
        return collectionItems.stream().map(this::toCollectionItemResponse).toList();
    }

    public CollectionItemResponse toCollectionItemResponse(Character collectionItem) {
        CollectionItemResponse collectionItemResponse = new CollectionItemResponse();
        collectionItemResponse.setId(collectionItem.getId());
        collectionItemResponse.setName(collectionItem.getName());
        collectionItemResponse.setImage(collectionItem.getImage());
        collectionItemResponse.setCreatedAt(collectionItem.getCreatedAt() != null ? collectionItem.getCreatedAt().toString() : null);
        collectionItemResponse.setUpdatedAt(collectionItem.getUpdatedAt() != null ? collectionItem.getUpdatedAt().toString() : null);
        return collectionItemResponse;
    }

}
