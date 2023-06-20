package gr.codelearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("123");
        logger.debug("test123");
        logger.error("error critical");
    }
}