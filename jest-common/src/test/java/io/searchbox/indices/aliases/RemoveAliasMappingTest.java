package io.searchbox.indices.aliases;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author cihat keser
 */
public class RemoveAliasMappingTest {

    @Test
    public void testBasicGetDataForJson() {
        RemoveAliasMapping addAliasMapping = new RemoveAliasMapping
                .Builder("tIndex", "tAlias")
                .build();
        String actualJson = new Gson().toJson(addAliasMapping.getData()).toString();
        String expectedJson = "[{\"remove\":{\"index\":\"tIndex\",\"alias\":\"tAlias\"}}]";

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGetDataForJsonWithFilter() {
        RemoveAliasMapping addAliasMapping = new RemoveAliasMapping
                .Builder("tIndex", "tAlias")
                .setFilter(AliasFilters.USER_KIMCHY_FILTER_JSON)
                .build();
        String actualJson = new Gson().toJson(addAliasMapping.getData()).toString();
        String expectedJson = "[{\"remove\":{\"index\":\"tIndex\",\"alias\":\"tAlias\",\"filter\":" + AliasFilters.USER_KIMCHY_FILTER_STRING + "}}]";

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGetDataForJsonWithFilterAndRouting() {
        RemoveAliasMapping addAliasMapping = new RemoveAliasMapping
                .Builder("tIndex", "tAlias")
                .setFilter(AliasFilters.USER_KIMCHY_FILTER_JSON)
                .addRouting("1")
                .build();
        String actualJson = new Gson().toJson(addAliasMapping.getData()).toString();
        String expectedJson = "[{\"remove\":{\"search_routing\":\"1\",\"index\":\"tIndex\",\"alias\":\"tAlias\",\"index_routing\":\"1\",\"filter\":" + AliasFilters.USER_KIMCHY_FILTER_STRING + "}}]";

        assertEquals(expectedJson, actualJson);
    }

}
