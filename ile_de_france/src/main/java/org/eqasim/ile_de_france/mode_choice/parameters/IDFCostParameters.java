package org.eqasim.ile_de_france.mode_choice.parameters;

import org.eqasim.core.simulation.mode_choice.ParameterDefinition;

public class IDFCostParameters implements ParameterDefinition {
	public double carCost_EUR_km = 0.0;

	public static IDFCostParameters buildDefault() {
		IDFCostParameters parameters = new IDFCostParameters();
		//according to MenDetails, the cost of a car is 7 thb/km
		//only gas around 3 thb/km
		parameters.carCost_EUR_km = 7;

		return parameters;
	}
}
