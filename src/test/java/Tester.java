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
    private SingerSummaryUntypeImpl singerSummaryUntype;

    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        singerSummaryUntype = ctx.getBean(SingerSummaryUntypeImpl.class);


    }
    @Test
    public void test() throws SQLException {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        SingerService src =  ctx.getBean(SingerService.class);
        System.out.println(src.findAll());
//        System.out.println(src.getConnection("tanrari","root").getSchema());
    }
    @Test
    public void findAllUnType(){
        singerSummaryUntype.displaySingerSummary();
    }

    @Test
    public void findById(){
//        ctx = new AnnotationConfigApplicationContext();
//        ctx.refresh();
//        SingerService src = ctx.getBean(SingerService.class);
        System.out.println(singerService.findById(1L));
    }

    @Test
    public void testFindAllWithAlbum (){
        List<Singer> singers =singerService.findAllWithAlbum();
        listSingersWithAlbumsAndInstruments(singers);

    }
    public static void listSingersWithAlbumsAndInstruments(List<Singer> singerList) {
        logger.info("--- Listing singers with instruments:");
        for (Singer singer : singerList) {
            logger.info("\t"+ singer.toString());
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
