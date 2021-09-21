package com.example.demo.servie;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.IModel;

import java.util.List;
import java.util.Optional;

public interface ModelService<T> {

    Optional<IModel<T>> refresh(IModel<T> model);

    IModel<T> saveModel(IModel<T> model);

    IModel<T> getModelById(Long id) throws NotFoundException;

    List<IModel<T>> getAllModels();

    IModel<T> updateModelById(IModel<T> newModel, Long id) throws NotFoundException;

    void deleteModelById(Long id) throws NotFoundException;
}
