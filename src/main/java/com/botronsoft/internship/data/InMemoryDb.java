package com.botronsoft.internship.data;

import com.botronsoft.internship.models.DeskCustomer;
import com.botronsoft.internship.models.Service;
import com.botronsoft.internship.models.Ticket;
import com.botronsoft.internship.Factories.TicketFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

@Repository
public class InMemoryDb {
    private ArrayList<Service> services = new ArrayList<>();
    private TicketFactory ticketFactory = new TicketFactory();
    private HashMap<String, Queue<Ticket>> serviceQ = new HashMap<>();
    private List<DeskCustomer> records = new ArrayList<>();

    public InMemoryDb() {
        registerServices();
    }

    private void registerServices() {
        StringBuilder stb = new StringBuilder();
        try {
            String path = new File("src\\main\\resources\\services.json").getCanonicalPath();

            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                stb.append(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stb.toString();

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Service.class, new ServiceInstanceCreator());
        Gson gson = gb.create();
        Service[] services = gson.fromJson(stb.toString(), Service[].class);

        for (Service service : services) {
            this.services.add(service);
        }
    }

    public ArrayList<Service> getAllServices() {
        return this.services;
    }

    public Ticket enroll(String serviceId) {
        Ticket ticket = this.ticketFactory.getTicket(serviceId, this.serviceQ);

        return ticket;
    }

    public Service getServiceById(String id) {
        for (Service service : services) {
            String serviceId = service.getId().toString();

            if (serviceId.equals(id)) {
                return service;
            }
        }
        return null;
    }

    public Ticket callForService(String[] idS, int deskNumber) {
        Ticket ticket = new Ticket("serviceId", Integer.MAX_VALUE);

        for (String serviceId : idS) {
            Queue<Ticket> currentServiceQueue = serviceQ.get(serviceId);
            if (currentServiceQueue.size() == 0) {
                continue;
            }
            Ticket ticketFromCurrentQ = currentServiceQueue.peek();

            if (ticket.getCustomerId() > ticketFromCurrentQ.getCustomerId()) {
                ticket = ticketFromCurrentQ;
            }
        }
        serviceQ.get(ticket.getServiceId()).poll();
        records.add(new DeskCustomer(deskNumber, ticket.getCustomerId()));
        return ticket;
    }

    public List<DeskCustomer> getTopRecords(int topRecords) {
        List<DeskCustomer> lastRecords = records.subList(records.size() - topRecords, records.size());
        Collections.reverse(lastRecords);
        return lastRecords;
    }

    private class ServiceInstanceCreator implements InstanceCreator<Service> {
        @Override
        public Service createInstance(Type t) {
            return new Service("name", "description");
        }
    }
}
