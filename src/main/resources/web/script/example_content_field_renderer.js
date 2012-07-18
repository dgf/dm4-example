// a simple field renderer that renders an example topic name with an additional CSS class
dm4c.add_field_renderer("dm4.example.content_field_renderer", {

    render_field: function(field_model, parent_element) {
        dm4c.render.field_label(field_model, parent_element)
        parent_element.append($("<span>").addClass('example').text(field_model.value))
    },

    render_form_element: function(field_model, parent_element) {
        var $content = dm4c.render.input(field_model)
        parent_element.append($content)
        return function() { // return input value
            return $.trim($content.val())
        }
    }
})
