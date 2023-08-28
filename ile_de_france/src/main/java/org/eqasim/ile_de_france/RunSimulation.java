package org.eqasim.ile_de_france;

import java.util.Set;

import org.eqasim.core.simulation.analysis.EqasimAnalysisModule;
import org.eqasim.core.simulation.mode_choice.EqasimModeChoiceModule;
import org.eqasim.ile_de_france.mode_choice.IDFModeChoiceModule;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.CommandLine;
import org.matsim.core.config.CommandLine.ConfigurationException;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.network.NetworkChangeEvent;
import org.matsim.core.network.NetworkChangeEvent.ChangeType;
import org.matsim.core.network.NetworkChangeEvent.ChangeValue;
import org.matsim.core.network.NetworkUtils;
import org.matsim.api.core.v01.network.Link;

public class RunSimulation {
	static public void main(String[] args) throws ConfigurationException {


		CommandLine cmd = new CommandLine.Builder(args) //
				.requireOptions("config-path") //
				.allowPrefixes("mode-choice-parameter", "cost-parameter") //
				.build();

		IDFConfigurator configurator = new IDFConfigurator();
		Config config = ConfigUtils.loadConfig(cmd.getOptionStrict("config-path"), configurator.getConfigGroups());
		
		// configure the time variant network here:
		config.network().setTimeVariantNetwork(true);

		config.controler().setOverwriteFileSetting(OverwriteFileSetting.deleteDirectoryIfExists);
		
		cmd.applyConfiguration(config);

		Scenario scenario = ScenarioUtils.createScenario(config);
		configurator.configureScenario(scenario);
		ScenarioUtils.loadScenario(scenario);
		configurator.adjustScenario(scenario);

		// ---

		System.out.println("Version 0.5 (2023-08-28)");

		for ( Link link : scenario.getNetwork().getLinks().values() ) {
			//convert to km/h
			int speed = (int) Math.round(link.getFreespeed()*3.6);
			Set<String> linkType = link.getAllowedModes();
			if(linkType.contains("car")	){
				if ( speed>80 ) {
					{
						//from 0 AM to 5 AM
						NetworkChangeEvent event = new NetworkChangeEvent(0*3600.) ;
						event.setFreespeedChange(new ChangeValue( ChangeType.FACTOR,  0.75));
						event.setFlowCapacityChange(new ChangeValue( ChangeType.FACTOR, 0.62*2  ));
						event.addLink(link);
						NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
					}
					{
						//from 5 AM to 9 AM
						NetworkChangeEvent event = new NetworkChangeEvent(5.*3600.) ;
						event.setFreespeedChange(new ChangeValue( ChangeType.FACTOR,  0.85));
						event.setFlowCapacityChange(new ChangeValue( ChangeType.FACTOR, 0.71*2  ));
						event.addLink(link);
						NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
					}
					{
						//from 9 AM to 1 PM
						NetworkChangeEvent event = new NetworkChangeEvent(9*3600.) ;
						event.setFreespeedChange(new ChangeValue( ChangeType.FACTOR,  0.75 ));
						event.setFlowCapacityChange(new ChangeValue( ChangeType.FACTOR, 0.79*2  ));
						event.addLink(link);
						NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
					}
					{
						//from 1 PM to 4 PM
						NetworkChangeEvent event = new NetworkChangeEvent(13*3600.) ;
						event.setFreespeedChange(new ChangeValue( ChangeType.FACTOR,  0.85 ));
						event.setFlowCapacityChange(new ChangeValue( ChangeType.FACTOR, 0.80*2  ));
						event.addLink(link);
						NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
					}
					{
						//from 4 PM to 7 PM
						NetworkChangeEvent event = new NetworkChangeEvent(16*3600.) ;
						event.setFreespeedChange(new ChangeValue( ChangeType.FACTOR, 1.75*0.65));
						event.setFlowCapacityChange(new ChangeValue( ChangeType.FACTOR, 0.86*2  ));
						event.addLink(link);
						NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
					}
					{
						//from 7 PM to 0 AM
						NetworkChangeEvent event = new NetworkChangeEvent(19*3600.) ;
						event.setFreespeedChange(new ChangeValue( ChangeType.FACTOR, 1.40*0.76));
						event.setFlowCapacityChange(new ChangeValue( ChangeType.FACTOR, 0.71*2  ));
						event.addLink(link);
						NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
					}
	
				}
				else
				{
				{
						//from 0 AM to 5 AM
						NetworkChangeEvent event = new NetworkChangeEvent(0*3600.) ;
						event.setFreespeedChange(new ChangeValue( ChangeType.FACTOR,  0.92));
						event.setFlowCapacityChange(new ChangeValue( ChangeType.FACTOR, 0.59*2  ));
						event.addLink(link);
						NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
					}
					{
						//from 5 AM to 9 AM
						NetworkChangeEvent event = new NetworkChangeEvent(5.*3600.) ;
						event.setFreespeedChange(new ChangeValue( ChangeType.FACTOR,  0.76));
						event.setFlowCapacityChange(new ChangeValue( ChangeType.FACTOR, 0.66*2  ));
						event.addLink(link);
						NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
					}
					{
						//from 9 AM to 1 PM
						NetworkChangeEvent event = new NetworkChangeEvent(9*3600.) ;
						event.setFreespeedChange(new ChangeValue( ChangeType.FACTOR,  0.70 ));
						event.setFlowCapacityChange(new ChangeValue( ChangeType.FACTOR, 0.70*2  ));
						event.addLink(link);
						NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
					}
					{
						//from 1 PM to 4 PM
						NetworkChangeEvent event = new NetworkChangeEvent(13*3600.) ;
						event.setFreespeedChange(new ChangeValue( ChangeType.FACTOR,  0.69 ));
						event.setFlowCapacityChange(new ChangeValue( ChangeType.FACTOR, 0.71*2  ));
						event.addLink(link);
						NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
					}
					{
						//from 4 PM to 7 PM
						NetworkChangeEvent event = new NetworkChangeEvent(16*3600.) ;
						event.setFreespeedChange(new ChangeValue( ChangeType.FACTOR, 1.25));
						event.setFlowCapacityChange(new ChangeValue( ChangeType.FACTOR, 0.76*2  ));
						event.addLink(link);
						NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
					}
					{
						//from 7 PM to 0 AM
						NetworkChangeEvent event = new NetworkChangeEvent(19*3600.) ;
						event.setFreespeedChange(new ChangeValue( ChangeType.FACTOR, 1.05));
						event.setFlowCapacityChange(new ChangeValue( ChangeType.FACTOR, 0.67*2  ));
						event.addLink(link);
						NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
					}
				}
				
			}
	

	}
		
		
		// ---
		

		Controler controller = new Controler(scenario);
		configurator.configureController(controller);
		controller.addOverridingModule(new EqasimAnalysisModule());
		controller.addOverridingModule(new EqasimModeChoiceModule());
		controller.addOverridingModule(new IDFModeChoiceModule(cmd));
		controller.run();
	}
}