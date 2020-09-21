package mz.ciuem.inamar.dao.imlp;

import mz.ciuem.inamar.dao.LogDao;
import mz.ciuem.inamar.entity.Log;

import org.springframework.stereotype.Repository;

@Repository("logDao")
public class LogDaoImpl extends GenericDaoImpl<Log> implements LogDao {

}
