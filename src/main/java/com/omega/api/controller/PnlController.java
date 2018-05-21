package com.omega.api.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omega.api.dto.PnlInput;
import com.omega.api.dto.PnlOutput;
import com.omega.api.service.PnlCalculatorService;

@Path("/")
@Component
public class PnlController {
	
	@Autowired
	private PnlCalculatorService pnlCalculatorService;
	
	@POST
	@Produces("application/json")
    @Consumes("application/json")
	@Path("/calc")
	public List<PnlOutput> calculate(PnlInput pnlInput) {
		List<PnlOutput> pnlOutputs = pnlCalculatorService.calculate(pnlInput);
		return pnlOutputs;
	}

}
