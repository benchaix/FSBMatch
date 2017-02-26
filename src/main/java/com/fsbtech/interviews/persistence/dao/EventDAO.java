package com.fsbtech.interviews.persistence.dao;

import java.util.List;
import java.util.Optional;

import com.fsbtech.interviews.entities.Event;
import com.fsbtech.interviews.entities.MarketRefType;

/**
 * Event DAO interface to implement
 * 
 * @author Ben Chaix
 */
public interface EventDAO {

  Optional<Event> getEventById(int id);

  void addEvent(Event event);

  void markEventAsCompleted(int id);

  void attachMarketRefTypeToEvent(int id, MarketRefType marketRefType);

  void removeMarketRefTypeFromEvent(Integer id, MarketRefType marketRefType);

  List<String> listFutureEvent(String cat, String subcat, String marketRefName);

  String dumpFullStructure();
}
