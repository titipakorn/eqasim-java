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

		System.out.println("Version 0.1 (2023-08-22)");

		for ( Link link : scenario.getNetwork().getLinks().values() ) {
			//convert to km/h
			int speed = (int) Math.round(link.getFreespeed()*3.6);
			Set<String> linkType = link.getAllowedModes();
			if(linkType.contains("car")	){
			if ( speed==30 ) {
				{
					//from 0 AM to 7 AM
					NetworkChangeEvent event = new NetworkChangeEvent(0*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*1));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 7 AM to 9 AM
					NetworkChangeEvent event = new NetworkChangeEvent(7.*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*1));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 9 AM to 4 PM
					NetworkChangeEvent event = new NetworkChangeEvent(9*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.96 ));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 4 PM to 7 PM
					NetworkChangeEvent event = new NetworkChangeEvent(16*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.94 ));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 7 PM to 0 AM
					NetworkChangeEvent event = new NetworkChangeEvent(19*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*1));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
			}
			else if ( speed==45 ) {
				{
					//from 0 AM to 7 AM
					NetworkChangeEvent event = new NetworkChangeEvent(0*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.88));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 7 AM to 9 AM
					NetworkChangeEvent event = new NetworkChangeEvent(7.*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.69));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 9 AM to 4 PM
					NetworkChangeEvent event = new NetworkChangeEvent(9*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.67 ));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 4 PM to 7 PM
					NetworkChangeEvent event = new NetworkChangeEvent(16*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.62 ));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 7 PM to 0 AM
					NetworkChangeEvent event = new NetworkChangeEvent(19*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.74));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}

			}
			else if ( speed==50 ) {
				{
					//from 0 AM to 7 AM
					NetworkChangeEvent event = new NetworkChangeEvent(0*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*1));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 7 AM to 9 AM
					NetworkChangeEvent event = new NetworkChangeEvent(7.*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.82));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 9 AM to 4 PM
					NetworkChangeEvent event = new NetworkChangeEvent(9*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.84 ));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 4 PM to 7 PM
					NetworkChangeEvent event = new NetworkChangeEvent(16*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.75 ));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 7 PM to 0 AM
					NetworkChangeEvent event = new NetworkChangeEvent(19*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.92));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}

			}
			else if ( speed==60 ) {
				{
					//from 0 AM to 7 AM
					NetworkChangeEvent event = new NetworkChangeEvent(0*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.72));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 7 AM to 9 AM
					NetworkChangeEvent event = new NetworkChangeEvent(7.*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.55));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 9 AM to 4 PM
					NetworkChangeEvent event = new NetworkChangeEvent(9*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.55 ));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 4 PM to 7 PM
					NetworkChangeEvent event = new NetworkChangeEvent(16*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.54 ));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 7 PM to 0 AM
					NetworkChangeEvent event = new NetworkChangeEvent(19*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.60));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}

			}
			else if ( speed==80 ) {
				{
					//from 0 AM to 7 AM
					NetworkChangeEvent event = new NetworkChangeEvent(0*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.59));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 7 AM to 9 AM
					NetworkChangeEvent event = new NetworkChangeEvent(7.*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.44));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 9 AM to 4 PM
					NetworkChangeEvent event = new NetworkChangeEvent(9*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.42 ));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 4 PM to 7 PM
					NetworkChangeEvent event = new NetworkChangeEvent(16*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.38 ));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 7 PM to 0 AM
					NetworkChangeEvent event = new NetworkChangeEvent(19*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.48));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}

			}
			else
			//speed=120
			{
				{
					//from 0 AM to 7 AM
					NetworkChangeEvent event = new NetworkChangeEvent(0*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.56));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 7 AM to 9 AM
					NetworkChangeEvent event = new NetworkChangeEvent(7.*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.46));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 9 AM to 4 PM
					NetworkChangeEvent event = new NetworkChangeEvent(9*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.41 ));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 4 PM to 7 PM
					NetworkChangeEvent event = new NetworkChangeEvent(16*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.36 ));
					event.addLink(link);
					NetworkUtils.addNetworkChangeEvent(scenario.getNetwork(),event);
				}
				{
					//from 7 PM to 0 AM
					NetworkChangeEvent event = new NetworkChangeEvent(19*3600.) ;
					event.setFreespeedChange(new ChangeValue( ChangeType.ABSOLUTE_IN_SI_UNITS,  speed*0.46));
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