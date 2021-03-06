package org.jboss.set.mjolnir.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.XsrfProtect;
import org.jboss.set.mjolnir.shared.domain.Report;
import org.jboss.set.mjolnir.shared.domain.ReportType;
import org.jboss.set.mjolnir.client.exception.ApplicationException;
import org.jboss.set.mjolnir.client.exception.ReportDataNotAvailableException;

import java.util.Map;

/**
 * @author Tomas Hofman (thofman@redhat.com)
 */
@RemoteServiceRelativePath("auth/ReportingService")
@XsrfProtect
public interface ReportingService extends RemoteService {

    Report generateReport(ReportType reportType) throws ApplicationException;

    Map<ReportType, String> getReportNames();

    void performReportAction(ReportType reportType, String uuid, String actionName) throws ReportDataNotAvailableException, ApplicationException;

    class Util {
        private static ReportingServiceAsync instance;

        public static ReportingServiceAsync getInstance() {
            if (instance == null) {
                instance = GWT.create(ReportingService.class);
            }
            return instance;
        }
    }

}
