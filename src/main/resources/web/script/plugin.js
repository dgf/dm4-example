
dm4c.add_plugin('de.deepamehta.example', function() {

    dm4c.load_page_renderer('/de.deepamehta.dm4-example/script/example_content_page_renderer.js')
    dm4c.load_field_renderer('/de.deepamehta.dm4-example/script/example_content_field_renderer.js')
    dm4c.load_stylesheet('/de.deepamehta.dm4-example/style/screen.css')

    function increaseExample() {
        var id = dm4c.selected_object.id,
            url = '/example/increase/' + id,
            example = dm4c.restc.request('GET', url)
        dm4c.do_select_topic(id)
    }

    var commands = [{
        context: ['context-menu', 'detail-panel-show'],
        label: 'Hit me!', handler: increaseExample
    }]

    dm4c.register_listener('topic_commands', function (topic) {
        return topic.type_uri === 'dm4.example.type' ? commands : []
    })
})
