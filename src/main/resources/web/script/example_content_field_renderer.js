// a simple field renderer that renders an example topic name with an additional CSS class
function ExampleContentFieldRenderer(field_model) {
    this.field_model = field_model
}

ExampleContentFieldRenderer.prototype.render_field = function(parent_element) {
    dm4c.render.field_label(this.field_model, parent_element)
    parent_element.append($("<span>").addClass('example').text(this.field_model.value))
}

ExampleContentFieldRenderer.prototype.render_form_element = function(parent_element) {
    var $content = dm4c.render.input(this.field_model)
    parent_element.append($content)
    return function() {
        return $.trim($content.val())
    }
}
