package com.demo.console.lifecycle;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component("flywayContext")
public class FlywayContext implements Serializable, SmartLifecycle {

  private static final Logger LOGGER = Logger.getLogger(FlywayContext.class);

  @Resource
  private DataSource c3p0DataSource;

  @Autowired
  public FlywayContext(DataSource c3p0DataSource) {

    LOGGER.info("Inside flywayContext deploying db-scripts...");
    this.c3p0DataSource = c3p0DataSource;

  }

  private void flywayinit() {
    LOGGER.info("Deploying database changes if any...");
    Flyway flyway = new Flyway();
    flyway.setDataSource(c3p0DataSource);

    MigrationInfoService infoService = flyway.info();

    if (null != infoService) {
      MigrationInfo[] info = infoService.all();
      if (null != info && info.length == 0) {
        flyway.baseline();
      }
    }

    flyway.setSqlMigrationPrefix("CAM_APP");
    flyway.setSqlMigrationSeparator("_");
    flyway.setBaselineOnMigrate(true);
    // Start the migration
    flyway.setLocations("db/migration");
    flyway.migrate();
    LOGGER.info("Deployed database successfully...");

  }

  @Override
  public boolean isRunning() {
    LOGGER.debug("flywayContext isRunning returned false");
    return false;
  }

  @Override
  public void start() {
    LOGGER.debug("Starting flyaway context calling flyway-init");
    flywayinit();
  }

  @Override
  public void stop() {
    LOGGER.debug("stoping flyway context");
  }

  @Override
  public int getPhase() {
    LOGGER.debug(" phase is set to 10");
    return 10;
  }

  @Override
  public boolean isAutoStartup() {
    LOGGER.debug("isAutoStartup is true");
    return true;
  }

  @Override
  public void stop(Runnable arg0) {
    LOGGER.debug("stoping flyway context in runnable...");
  }

}
