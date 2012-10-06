dm4c.add_plugin('dm4.example.plugin', function() {

    // calls the alternative REST creation method with customized JSON format
    function createAnotherExample() {
        var name = prompt('Example name', 'Another Example'),
            topic = dm4c.restc.request('POST', '/example/create', { name: name })
        dm4c.show_topic(new Topic(topic), 'show')
    }

    // calls the server side increase method of the selected Example topic
    function increaseExample() {
        var url = '/example/increase/' + dm4c.selected_object.id,
            topic = dm4c.restc.request('GET', url)
        dm4c.show_topic(new Topic(topic), 'show')
    }

    // define type specific commands and register them
    dm4c.add_listener('topic_commands', function (topic) {
        return topic.type_uri !== 'dm4.example.type' ? [] : [{
            context: ['context-menu', 'detail-panel-show'],
            label: 'Increase me!',
            handler: increaseExample
        }]
    })

    // register an additional create command
    dm4c.add_listener('post_refresh_create_menu', function(type_menu) {
        if (dm4c.has_create_permission('dm4.example.type')) {
            type_menu.add_separator()
            type_menu.add_item({
              label: 'New Example',
              handler: createAnotherExample
            })
        }
    })
})
