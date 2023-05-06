package entities;

//import jakarta.persistence.EntityManager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {
    final static String ALL_SINGER_NATIVE_QUERY =  "SELECT * FROM singer";
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
        return em.createNamedQuery(Singer.FIND_ALL, Singer.class).getResultList();
    }
}
