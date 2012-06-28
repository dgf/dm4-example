// a page render that simply renders the value of an example content topic instance
function ExampleContentPageRenderer() {

    this.render_page = function(topic) {
        dm4c.render.field_label('Content')
        dm4c.render.page(topic.value)
    }

    this.render_form = function(topic) {
        var $content = dm4c.render.input(topic.value)
        dm4c.render.field_label('Content')
        dm4c.render.page($content)
        return function () {
            topic.value = $.trim($content.val())
            dm4c.do_update_topic(topic)
            dm4c.page_panel.refresh()
        }
    }
}