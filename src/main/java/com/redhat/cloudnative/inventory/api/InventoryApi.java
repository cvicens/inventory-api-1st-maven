/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.3.4).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.redhat.cloudnative.inventory.api;

import com.redhat.cloudnative.inventory.model.GenericError;
import com.redhat.cloudnative.inventory.model.InventoryItem;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import io.micrometer.core.instrument.Metrics;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-07-04T00:30:25.754+03:00[Asia/Riyadh]")

@Validated
@Api(value = "inventory", description = "the inventory API")
public interface InventoryApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "Get all inventory items", nickname = "inventoryGet", notes = "Should return all elements as an array of InventoryItems or an empty array if there are none", response = InventoryItem.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Should return an arry of InventoryItems", response = InventoryItem.class, responseContainer = "List") })
    @RequestMapping(value = "/inventory",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<InventoryItem>> inventoryGet() {
        // Prometheus metric
        Metrics.counter("api.http.requests.total", "api", "inventory", "method", "GET", "endpoint", 
        "/inventory").increment();
        // <<< Prometheus metric

        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"itemId\" : \"329299\",  \"quantity\" : 35}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Get one InventoryItem", nickname = "inventoryItemIdGet", notes = " Returns the item for the id provided or an error", response = InventoryItem.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Should return the item for the id provided", response = InventoryItem.class),
        @ApiResponse(code = 404, message = "Item not found", response = GenericError.class) })
    @RequestMapping(value = "/inventory/{itemId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<InventoryItem> inventoryItemIdGet(@ApiParam(value = "",required=true) @PathVariable("itemId") String itemId) {
        // >>> Prometheus metric
        Metrics.counter("api.http.requests.total", "api", "inventory", "method", "GET", "endpoint", 
        "/inventory/" + itemId).increment();
        // <<< Prometheus metric
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"itemId\" : \"329299\",  \"quantity\" : 35}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
