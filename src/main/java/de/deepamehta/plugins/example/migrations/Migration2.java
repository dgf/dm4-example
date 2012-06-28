package de.deepamehta.plugins.example.migrations;

import de.deepamehta.core.model.TopicModel;
import de.deepamehta.core.service.Migration;

/**
 * update search icon back to the good old bucket
 */
public class Migration2 extends Migration {

    @Override
    public void run() {
        // get web client icon configuration of the search topic
        TopicModel iconConfig = dms.getTopicType("dm4.webclient.search", null)//
                .getViewConfig().getConfigTopic("dm4.webclient.view_config")//
                .getCompositeValue().getTopic("dm4.webclient.icon");

        // update icon value
        iconConfig.setSimpleValue("/de.deepamehta.dm4-example/images/bucket.png");
        dms.updateTopic(iconConfig, null);
    }

}
