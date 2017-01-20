/*
 * Creation : 9 mars 2015
 */
package com.opengroup.res.util.configuration;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import org.apache.commons.lang.StringUtils;

import java.io.File;

/**
 * Startup Logger Listener - Auto retrieve logs home folder
 *
 * @author Open group
 * @since 1.0.0
 */
public class LoggerStartupListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

    private static final String LOGBACK_CONFIGURATION_KEY_ROOT_LOGS_FOLDER = "LOGS_HOME";

    private static final String SCM_HOME_ENV = "POP_BOOT_HOME";

    private boolean started;

    @Override
    public void start() {
        StringBuilder logPathBuilder = new StringBuilder();
        String logsRootPathEnv = System.getenv(SCM_HOME_ENV);
        if (!StringUtils.isEmpty(logsRootPathEnv)) {
            logPathBuilder.append(logsRootPathEnv);
            logPathBuilder.append(File.separator);
            logPathBuilder.append("logs");
        } else {
            logPathBuilder.append("/var/log");
        }
        context.putProperty(LOGBACK_CONFIGURATION_KEY_ROOT_LOGS_FOLDER, logPathBuilder.toString());
        this.started = true;
    }

    @Override
    public void stop() {
        //Default
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public boolean isResetResistant() {
        return false;
    }

    @Override
    public void onStart(LoggerContext context) {
        //Default

    }

    @Override
    public void onReset(LoggerContext context) {
        //Default
    }

    @Override
    public void onStop(LoggerContext context) {
        //Default
    }

    @Override
    public void onLevelChange(Logger logger, Level level) {
        //Default
    }
}
