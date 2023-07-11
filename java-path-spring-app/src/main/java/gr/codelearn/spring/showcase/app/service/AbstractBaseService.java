package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.model.BaseEntity;
import gr.codelearn.spring.showcase.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) //phantom reads, committed changes, range queries
public abstract class AbstractBaseService<T extends BaseEntity> implements BaseService<T, Long>{

	protected abstract JpaRepository<T, Long> getRepository();

	@Override
	public T create(T entity){
		return getRepository().save(entity);
	}

	@Override
	public void update(T entity){
		if (exists(entity)){
			getRepository().save(entity);
		} else {
			throw new RuntimeException("Customer does not exist");
		}
	}

	@Override
	public List<T> createAll(List<T> entities){
		return getRepository().saveAll(entities);
	}

	@Override
	public List<T> createAll(T... entities){
		return createAll(List.of(entities));
	}

	@Override
	public void delete(T entity){
		if (exists(entity)){
			getRepository().delete(entity);
		} else {
			throw new RuntimeException("Customer does not exist");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean exists(T entity) {
		return getRepository().existsById(entity.getId());
	}

	@Override
	@Transactional(readOnly = true)
	public T get(final Long id) {
		return getRepository().getReferenceById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll(){
		return getRepository().findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Long count(){
		return getRepository().count();
	}
}
