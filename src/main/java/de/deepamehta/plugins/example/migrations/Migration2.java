package de.deepamehta.plugins.example.migrations;

import de.deepamehta.core.service.Migration;

/**
 * Update search icon back to the good old bucket.
 */
public class Migration2 extends Migration {

    @Override
    public void run() {
        // update web client icon configuration of the search topic
        dms.getTopicType("dm4.webclient.search", null)
                .getViewConfig()
                .addSetting("dm4.webclient.view_config", "dm4.webclient.icon",
                        "de.deepamehta.dm4-example/images/bucket.png");
    }
}
