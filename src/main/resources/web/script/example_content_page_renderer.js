// a page render that simply renders the value of an example content topic instance
dm4c.add_page_renderer("dm4.example.content_page_renderer", {

    render_page: function(topic) {
        dm4c.render.field_label('Content')
        dm4c.render.page(topic.value)
    },

    render_form: function(topic) {
        var $content = dm4c.render.input(topic.value)
        dm4c.render.field_label('Content')
        dm4c.render.page($content)
        return function () { // update topic with value of input
            topic.value = $.trim($content.val())
            dm4c.do_update_topic(topic)
            dm4c.page_panel.refresh()
        }
    }
})
