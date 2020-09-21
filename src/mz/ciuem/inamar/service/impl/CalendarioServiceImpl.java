package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.CalendarioDao;
import mz.ciuem.inamar.entity.Calendario;
import mz.ciuem.inamar.service.CalendarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("calendarioService")
public class CalendarioServiceImpl extends GenericServiceImpl<Calendario> implements CalendarioService{
         @Autowired
         private CalendarioDao calendarioDao;

		@Override
		public List<Calendario> buscarCalendarioPorCandidato(String cod) {
			// TODO Auto-generated method stub
			return calendarioDao.buscarCalendarioPorCandidato(cod);
		}
}
