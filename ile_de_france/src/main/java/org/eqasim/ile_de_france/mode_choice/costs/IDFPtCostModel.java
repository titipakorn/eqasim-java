package org.eqasim.ile_de_france.mode_choice.costs;

import java.util.List;

import org.eqasim.core.simulation.mode_choice.cost.CostModel;
import org.eqasim.ile_de_france.mode_choice.utilities.predictors.IDFPersonPredictor;
import org.eqasim.ile_de_france.mode_choice.utilities.predictors.IDFSpatialPredictor;
import org.eqasim.ile_de_france.mode_choice.utilities.variables.IDFPersonVariables;
import org.eqasim.ile_de_france.mode_choice.utilities.variables.IDFSpatialVariables;
import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.contribs.discrete_mode_choice.model.DiscreteModeChoiceTrip;
import org.matsim.core.utils.geometry.CoordUtils;
import org.matsim.pt.routes.TransitPassengerRoute;
import org.matsim.pt.transitSchedule.api.TransitSchedule;

import com.google.inject.Inject;

public class IDFPtCostModel implements CostModel {
	private final IDFPersonPredictor personPredictor;
	private final IDFSpatialPredictor spatialPredictor;

	// TODO: This should be hidden by some custom predictor
	private final TransitSchedule transitSchedule;

	@Inject
	public IDFPtCostModel(IDFPersonPredictor personPredictor, IDFSpatialPredictor spatialPredictor,
			TransitSchedule transitSchedule) {
		this.personPredictor = personPredictor;
		this.spatialPredictor = spatialPredictor;
		this.transitSchedule = transitSchedule;
	}

	private boolean isMRT(List<? extends PlanElement> elements) {
		for (PlanElement element : elements) {
			if (element instanceof Leg) {
				Leg leg = (Leg) element;

				if (leg.getMode().equals(TransportMode.pt)) {
					TransitPassengerRoute route = (TransitPassengerRoute) leg.getRoute();

					String transportMode = transitSchedule.getTransitLines().get(route.getLineId()).getRoutes()
							.get(route.getRouteId()).getTransportMode().toLowerCase();

					if (!transportMode.equals("bus") && !transportMode.equals("subway")) {
						return false;
					}
				}
			}
		}

		return true;
	}

	
	private boolean isBTS(List<? extends PlanElement> elements) {
		for (PlanElement element : elements) {
			if (element instanceof Leg) {
				Leg leg = (Leg) element;

				if (leg.getMode().equals(TransportMode.pt)) {
					TransitPassengerRoute route = (TransitPassengerRoute) leg.getRoute();

					String transportMode = transitSchedule.getTransitLines().get(route.getLineId()).getRoutes()
							.get(route.getRouteId()).getTransportMode().toLowerCase();

					if (!transportMode.equals("bus") && !transportMode.equals("subway")) {
						return false;
					}
				}
			}
		}

		return true;
	}


	// private final static Coord CENTER = new Coord(651726, 6862287);

	// private double calculateBasisDistance_km(DiscreteModeChoiceTrip trip) {
	// 	return 1e-3 * (CoordUtils.calcEuclideanDistance(CENTER, trip.getOriginActivity().getCoord())
	// 			+ CoordUtils.calcEuclideanDistance(CENTER, trip.getDestinationActivity().getCoord()));
	// }

	@Override
	public double calculateCost_MU(Person person, DiscreteModeChoiceTrip trip, List<? extends PlanElement> elements) {

		boolean isBTS = isBTS(elements);
		boolean isMRT = isMRT(elements);

		if (isMRT) {
			return 32;
		}


		if (isBTS) {
			return 38;
		}

		return 20;
	}
}
