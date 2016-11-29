package com.ufc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ufc.model.Instituicao;

public interface InstituicaoRepository extends MongoRepository<Instituicao, String> {

	Instituicao findByCnpj(String cnpj);

}
