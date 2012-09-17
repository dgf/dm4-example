// register a multi renderer
dm4c.add_multi_renderer('dm4.example.content_multi_renderer', {

    // show a div for each page model
    render_info: function (pages, $parent, level) {
        dm4c.render.field_label(pages[0].topic_type.value, $parent)
        $.each(pages, function (p, page) {
            if (p.id !== -1) { // ignore empty page model
                $parent.append($('<div>').addClass('example').append(page.value))
            }
        })
    },

    render_form: function (pages, $parent, level) {
        var type = pages[0].topic_type,
            assoc_def = pages[0].assoc_def,
            uri = pages[0].uri,
            parent = pages[0].toplevel_topic,
            // TODO sync remove call with pages model
            TR = dm4c.get_page_renderer('dm4.webclient.topic_renderer'),
            $add = dm4c.ui.button(addContent, 'Add ' + type.value),
            $addDiv = $('<div>').addClass('add-button').append($add)

        // callback to create, cache and render a new empty topic model
        function addContent() {
            var topic = dm4c.empty_topic(type.uri),
                page = TR.create_page_model(topic, assoc_def, uri, parent, TR.mode.FORM)
            pages.push(page)
            TR.render_page_model(page, TR.mode.FORM, level, $addDiv, true)
        }

        // render each page model and append the button
        $.each(pages, function (p, page) {
            TR.render_page_model(page, TR.mode.FORM, level, $parent)
        })
        $parent.append($addDiv)

        // return a topic model for each content aggregation
        return function () {
            var values = []
            $.each(pages, function (p, page) {
                var value = TR.build_topic_model(page)
                if (value != null) {
                    values.push(value)
                }
            })
            return values
        }
    }
})
