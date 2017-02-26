package com.fsbtech.interviews;

import java.util.Collection;
import java.util.Optional;

import com.fsbtech.interviews.entities.Event;
import com.fsbtech.interviews.entities.MarketRefType;
import com.fsbtech.interviews.persistence.dao.EventDAO;
import com.fsbtech.interviews.persistence.dao.impl.InMemoryEventDAO;
import com.fsbtech.interviews.utils.JSONUtils;

public class CustomClient implements Client {

  EventDAO eventDAO = new InMemoryEventDAO();

  @Override
  public void addEvent(Event event) {
    eventDAO.addEvent(event);
  }

  @Override
  public void eventCompleted(Integer id) {
    eventDAO.markEventAsCompleted(id);
  }

  @Override
  public void attachMarketRefTypeToEvent(Integer id, MarketRefType marketRefType) {
    eventDAO.attachMarketRefTypeToEvent(id, marketRefType);
  }

  @Override
  public void removeMarketRefTypeFromEvent(Integer id, MarketRefType marketRefType) {
    eventDAO.removeMarketRefTypeFromEvent(id, marketRefType);
  }

  @Override
  public Collection<String> futureEventNamesCollection(String cat, String subcat, String marketRefName) {
    return eventDAO.listFutureEvent(cat, subcat, marketRefName);
  }

  @Override
  public String dumpFullStructure() {
    return JSONUtils.getPrettyJSONString(eventDAO.dumpFullStructure());
  }

  public Optional<Event> getEventById(int id) {
    return eventDAO.getEventById(id);
  }
}
