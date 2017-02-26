package com.fsbtech.interviews.persistence.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fsbtech.interviews.entities.Event;
import com.fsbtech.interviews.entities.MarketRefType;
import com.fsbtech.interviews.persistence.dao.EventDAO;

/**
 * Manage in memory Events
 * 
 * @author Ben Chaix
 */
public class InMemoryEventDAO implements EventDAO {

  // store events in a concurrent hash map
  Map<Integer, Event> eventMap = new ConcurrentHashMap<>();

  public InMemoryEventDAO() {
  }

  @Override
  public Optional<Event> getEventById(int id) {
    return Optional.ofNullable(eventMap.get(id));
  }

  @Override
  public void addEvent(Event event) {
    eventMap.putIfAbsent(event.getId(), event);
  }

  @Override
  public void markEventAsCompleted(int id) {
    if (eventMap.containsKey(id)) {
      Event event = eventMap.get(id);
      Event newEvent = new Event(id, event.getName(), event.getSubCategory(), event.getMarketRefTypes(), true);
      eventMap.replace(id, newEvent);
    }
  }

  @Override
  public void attachMarketRefTypeToEvent(int id, MarketRefType marketRefType) {
    if (eventMap.containsKey(id)) {
      Event event = eventMap.get(id);
      event.attachMarketRefType(marketRefType);
    }
  }

  @Override
  public void removeMarketRefTypeFromEvent(Integer id, MarketRefType marketRefType) {
    if (eventMap.containsKey(id)) {
      Event event = eventMap.get(id);
      event.removeMarketRefType(marketRefType);
    }
  }

  @Override
  public List<String> listFutureEvent(String cat, String subcat, String marketRefName) {
    return eventMap
        .entrySet()
        .parallelStream()
        .filter(e -> !e.getValue().getCompleted()
            && (cat == null || e.getValue().getSubCategory().getCategory().getRef().equals(cat))
            && (subcat == null || e.getValue().getSubCategory().getRef().equals(subcat))
            && (marketRefName == null || e.getValue().hasMarketName(marketRefName)))
        .map(e -> e.getValue().getName())
        .collect(Collectors.toList());
  }

  // dump non completed event
  public String dumpEvent() {
    String events = eventMap
        .entrySet()
        .parallelStream()
        .filter(e -> !e.getValue().getCompleted())
        .map(e -> e.getValue().toJsonString())
        .collect(Collectors.joining(",", "{\"Events\": [", "]}"));
    return events;
  }

  // dump full structure into JSON
  @Override
  public String dumpFullStructure() {
    Supplier<Stream<Event>> streamSupplier = () -> eventMap
        .entrySet()
        .parallelStream()
        .filter(e -> !e.getValue().getCompleted())
        .map(e -> e.getValue());

    String categories = streamSupplier
        .get()
        .map(e -> e.getSubCategory().getCategory())
        .distinct()
        .map(c -> c.toJsonString())
        .collect(Collectors.joining(",", "\"Categories\": [", "]"));

    String subCategories = streamSupplier
        .get()
        .map(e -> e.getSubCategory())
        .distinct()
        .map(s -> s.toJsonString())
        .collect(Collectors.joining(",", "\"Sub Categories\": [", "]"));

    String markets = streamSupplier
        .get()
        .flatMap(e -> e.getMarketRefTypes().stream())
        .distinct()
        .map(m -> m.toJsonString())
        .collect(Collectors.joining(",", "\"Market Refs\": [", "]"));

    String events = streamSupplier
        .get()
        .map(e -> e.toJsonString())
        .collect(Collectors.joining(",", "\"Events\": [", "]"));

    StringJoiner joiner = new StringJoiner(",", "{", "}");
    joiner.add(categories);
    joiner.add(subCategories);
    joiner.add(markets);
    joiner.add(events);
    return joiner.toString();
  }
}
