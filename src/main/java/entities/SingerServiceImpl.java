package entities;

//import jakarta.persistence.EntityManager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {

    @PersistenceContext
    public  EntityManager em;
    private static Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public List findAll() {

        return em.createNamedQuery(Singer.FIND_ALL,Singer.class).getResultList();
//        return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY).getResultList();
    }

    @Override
    public List<Singer> findAllWithAlbum() {

        return em.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
    }
    @Override
    public Singer findById(Long id){
        TypedQuery<Singer> query = em.createNamedQuery(Singer.FIND_SINGER_BY_ID,Singer.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }
}
