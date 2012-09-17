// a simple example that renders a topic name with a additional CSS class
dm4c.add_simple_renderer('dm4.example.name_simple_renderer', {

    render_info: function(model, $parent) {
        dm4c.render.field_label(model, $parent)
        $parent.append($("<span>").addClass('example').text(model.value))
    },

    render_form: function(model, $parent) {
        var $name = dm4c.render.input(model)
        $parent.append($name)
        return function() { // return input value
            return $.trim($name.val())
        }
    }
})
