package mz.ciuem.inamar.dao.imlp;

import mz.ciuem.inamar.dao.ErrorLogDao;
import mz.ciuem.inamar.entity.ErrorLog;

import org.springframework.stereotype.Repository;

@Repository
public class ErrorLogDaoImpl extends GenericDaoImpl<ErrorLog> implements
		ErrorLogDao {

}
