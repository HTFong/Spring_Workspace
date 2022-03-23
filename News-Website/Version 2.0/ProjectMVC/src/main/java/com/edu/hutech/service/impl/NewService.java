package com.edu.hutech.service.impl;

import com.edu.hutech.converter.NewConverter;
import com.edu.hutech.dto.NewDTO;
import com.edu.hutech.entity.CategoryEntity;
import com.edu.hutech.entity.NewEntity;
import com.edu.hutech.repository.CategoryRepository;
import com.edu.hutech.repository.NewRepository;
import com.edu.hutech.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class NewService implements INewService {

    @Autowired
    private NewRepository newRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private NewConverter newConverter;
    @Override
    public List<NewDTO> findAll(Pageable pageable) {
        List<NewDTO> models = new ArrayList<>();
        List<NewEntity> entities = newRepository.findAll(pageable).getContent();
        for (NewEntity item: entities) {
            NewDTO newDTO = newConverter.toDto(item);
            models.add(newDTO);
        }
        return models;
    }

    @Override
    public int getTotalItem() {
        return (int) newRepository.count();
    }

    @Override
    public NewDTO findById(long id) {
        NewEntity entity = newRepository.findById(id);
        NewDTO newDTO = newConverter.toDto(entity);
        return newDTO;
    }


    @Override
    @Transactional //phải có transaction qlí rollback,commit
    public NewDTO save(NewDTO dto) {
        NewEntity entity = new NewEntity();
        if (dto.getId() == null){
            //ko có id (thêm mới ko có id)
            entity = newConverter.toEntity(dto); //convert DTO>entity
        } else {
            //có id thì get oldNew(có createdBy,Date..) từ db lên
            NewEntity oldNew = newRepository.findOne(dto.getId());
            entity = newConverter.toEntity(oldNew, dto); //convert DTO>entity, với entity cũ(oldNew) vẫn giữ nguyên data(createdBy,Date...)
        }
        CategoryEntity categoryEntity = categoryRepository.findByCode(dto.getCategoryCode()); //categoryEntity set vào field category(NewEntity)
        entity.setCategory(categoryEntity);
        return newConverter.toDto(newRepository.save(entity));
    }

    @Override
    public void delete(long[] ids) {
        for (long id: ids) {
            newRepository.delete(id);
        }
    }
}
