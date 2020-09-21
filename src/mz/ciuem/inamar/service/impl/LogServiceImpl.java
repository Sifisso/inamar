package mz.ciuem.inamar.service.impl;

import org.springframework.stereotype.Service;

import mz.ciuem.inamar.entity.Log;
import mz.ciuem.inamar.service.LogService;

@Service("logService")
public class LogServiceImpl extends GenericServiceImpl<Log>implements LogService {

}
