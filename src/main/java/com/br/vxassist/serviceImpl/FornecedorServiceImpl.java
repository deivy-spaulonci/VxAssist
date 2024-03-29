package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.FornecedorDTO;
import com.br.vxassist.filter.FornecedorFilter;
import com.br.vxassist.mapper.FornecedorMapper;
import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.model.QFornecedor;
import com.br.vxassist.repository.FornecedorRepository;
import com.br.vxassist.service.ServiceInterface;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FornecedorServiceImpl implements ServiceInterface<FornecedorDTO, FornecedorFilter> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    private final FornecedorMapper fornecedorMapper = FornecedorMapper.INSTANCE;

    @Override
    public List<FornecedorDTO> get(FornecedorFilter fornecedorFilter, Sort sort){
        List<Fornecedor> forenecedores = new ArrayList<>();
        fornecedorRepository.findAll(this.getForenecedorPredicate(fornecedorFilter), sort).forEach(forenecedores::add);
        return fornecedorMapper.toFornecedorDtoList(forenecedores);
    }

    @Override
    public Page<FornecedorDTO> getPage(FornecedorFilter fornecedorFilter, Pageable pageable) {
        return fornecedorRepository.findAll(this.getForenecedorPredicate(fornecedorFilter), pageable).map(fornecedorMapper::toDTO);
    }

    @Override
    public Long count() {
        return fornecedorRepository.count();
    }

    @Override
    public void excluir(Long id) {

    }

    public List<Fornecedor> getSelect(FornecedorFilter fornecedorFilter){
        QFornecedor qFornecedor = QFornecedor.fornecedor;
        JPAQuery query = new JPAQuery(entityManager);
        query.select(qFornecedor.id, qFornecedor.nome);
        query.from(qFornecedor);
        if(Objects.nonNull(fornecedorFilter)){
            if((Objects.nonNull(fornecedorFilter.nome) && Objects.nonNull(fornecedorFilter.nome))
                || (Objects.nonNull(fornecedorFilter.razaoSocial))){
                query.where(qFornecedor.nome.toLowerCase().likeIgnoreCase("%"+fornecedorFilter.nome.toLowerCase()+"%")
                        .or(qFornecedor.razaoSocial.toLowerCase().likeIgnoreCase("%"+fornecedorFilter.razaoSocial.toLowerCase()+"%")));
            }
            else if(Objects.nonNull(fornecedorFilter.cnpj)){
                query.where(qFornecedor.cnpj.likeIgnoreCase("%"+fornecedorFilter.cnpj+"%"));
            }

        }
        return query.fetch();
    }

    @Override
    public FornecedorDTO create(FornecedorDTO fornecedorDTO) {
        FornecedorFilter fornecedorFilter = new FornecedorFilter();
        fornecedorFilter.setCnpj(fornecedorDTO.getCnpj());
        List<Fornecedor>  lista  = this.getSelect(fornecedorFilter);
        if(Objects.nonNull(lista) && !lista.isEmpty()){
            throw new RuntimeException("Já existe um fornecedor com esse CNPJ");
        }else{
            return fornecedorMapper.toDTO(fornecedorRepository.save(fornecedorMapper.toModel(fornecedorDTO)));
        }
    }

    @Override
    public void update(FornecedorDTO dto) {

    }

    @Override
    public FornecedorDTO findById(Long id) {
        return fornecedorMapper.toDTO(fornecedorRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Predicate getForenecedorPredicate(FornecedorFilter fornecedorFilter){
        QFornecedor qFornecedor = QFornecedor.fornecedor;

        BooleanBuilder where = new BooleanBuilder();
        if(Objects.nonNull(fornecedorFilter.id)){
            where.and(qFornecedor.id.eq(fornecedorFilter.id));
        }

        if(Objects.nonNull(fornecedorFilter.nome) && !fornecedorFilter.nome.trim().isEmpty()
            || Objects.nonNull(fornecedorFilter.razaoSocial) && !fornecedorFilter.razaoSocial.trim().isEmpty()){
            where.and(qFornecedor.nome.toLowerCase().likeIgnoreCase("%"+fornecedorFilter.nome.toLowerCase()+"%"));
            where.or(qFornecedor.razaoSocial.toLowerCase().likeIgnoreCase("%"+fornecedorFilter.razaoSocial.toLowerCase()+"%"));
        }

        if(Objects.nonNull(fornecedorFilter.cnpj) && !fornecedorFilter.cnpj.trim().isEmpty()){
            where.and(qFornecedor.cnpj.likeIgnoreCase("%"+fornecedorFilter.cnpj+"%"));
        }

        if(Objects.nonNull(fornecedorFilter.cidade)){
            if(Objects.nonNull(fornecedorFilter.cidade.getId())){
                where.and(qFornecedor.cidade.id.eq(fornecedorFilter.cidade.getId()));
            }else if (!fornecedorFilter.cidade.getNome().trim().isEmpty()){
                where.and(qFornecedor.cidade.nome.likeIgnoreCase("%"+fornecedorFilter.cidade.getNome()+"%"));
            }

        }
        return where;
    }
}
