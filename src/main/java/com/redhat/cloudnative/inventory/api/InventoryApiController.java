package com.redhat.cloudnative.inventory.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import io.micrometer.core.instrument.Metrics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.redhat.cloudnative.inventory.data.InventoryRepository;
import com.redhat.cloudnative.inventory.model.InventoryItem;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-23T11:23:32.594+02:00[Europe/Madrid]")

@Controller
@RequestMapping("${openapi.inventory.base-path:/api}")
public class InventoryApiController implements InventoryApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    private InventoryRepository inventoryRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public InventoryApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<InventoryItem>> inventoryGet() {
        // >>> Prometheus metric
        Metrics.counter("api.http.requests.total", "api", "inventory", "method", "GET", "endpoint", "/inventory").increment();
        // <<< Prometheus metric

        List<InventoryItem> items = new ArrayList<InventoryItem>();

        try {
            getRequest().ifPresent(request -> {
                for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        HttpServletResponse res = request.getNativeResponse(HttpServletResponse.class);
                        res.setCharacterEncoding("UTF-8");
                        res.addHeader("Content-Type", "application/json");
                        
                        List<InventoryItem> _items = inventoryRepository.findAll();
                        items.addAll(_items);
                        break;
                    }
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<List<InventoryItem>>(items, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InventoryItem> inventoryItemIdGet(@PathVariable("itemId") String itemId) {
        // >>> Prometheus metric
        Metrics.counter("api.http.requests.total", "api", "inventory", "method", "GET", "endpoint", "/inventory/" + itemId).increment();
        // <<< Prometheus metric

        InventoryItem item = new InventoryItem();
        try {
            getRequest().ifPresent(request -> {
                for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        HttpServletResponse response = request.getNativeResponse(HttpServletResponse.class);
                        response.setCharacterEncoding("UTF-8");
                        response.addHeader("Content-Type", "application/json");
                        
                        InventoryItem _item = inventoryRepository.findByItemId(itemId);
                        if (_item != null) {
                            item.setItemId(_item.getItemId());
                            item.setQuantity(_item.getQuantity());
                        }
                        break;
                    }
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        if (item.getItemId() == null) {
            return new ResponseEntity<InventoryItem>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<InventoryItem>(item, HttpStatus.OK);
    }

}