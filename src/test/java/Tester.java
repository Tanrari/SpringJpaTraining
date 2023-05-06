import config.JpaConfig;
import entities.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class Tester {
    private static Logger logger= LoggerFactory.getLogger(Tester.class);
    private SingerService singerService;
    private GenericApplicationContext ctx;

    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = ctx.getBean(SingerService.class);

    }
    @Test
    public void test() throws SQLException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        SingerService src = (SingerService) ctx.getBean(SingerService.class);
        System.out.println(src.findAll());
//        System.out.println(src.getConnection("tanrari","root").getSchema());
    }

    @Test
    public void testFindAllWithAlbum (){
        List<Singer> singers =singerService.findAllWithAlbum();
        listSingersWithAlbumsAndInstruments(singers);

    }
    public static void listSingersWithAlbumsAndInstruments(List<Singer> singerList) {
        logger.info("--- Listing singers with instruments:");
        for (Singer singer : singerList) {
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    logger.info("\t" + album.toString());
                }
            }
            if (singer.getInstruments() != null) {
                for (Instrument instrument : singer.getInstruments()) {
                    logger.info("\t" + instrument.getInstrumentId().toString());
                }

            }

        }
    }
}
