// a simple example that renders a topic name with a additional CSS class
dm4c.add_simple_renderer('dm4.example.content_field_renderer', {

    render_info: function(model, $parent) {
        dm4c.render.field_label(model, $parent)
        $parent.append($("<span>").addClass('example').text(model.value))
    },

    render_form: function(model, $parent) {
        var $content = dm4c.render.input(model)
        $parent.append($content)
        return function() { // return input value
            return $.trim($content.val())
        }
    }
})
