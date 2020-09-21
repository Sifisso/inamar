package mz.ciuem.inamar.service.impl;

import mz.ciuem.inamar.entity.ErrorLog;
import mz.ciuem.inamar.service.ErrorLogService;

import org.springframework.stereotype.Service;

@Service("errorLogService")
public class ErrorLogServiceImpl extends GenericServiceImpl<ErrorLog>
		implements ErrorLogService {

}
