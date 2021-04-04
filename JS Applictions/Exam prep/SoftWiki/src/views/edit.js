import { html } from '../../node_modules/lit-html/lit-html.js';
import { getItemById, editArticle } from '../api/data.js';

const editTemplate = (item, onSubmit) => html`
<section id="edit-page" class="content">
    <h1>Edit Article</h1>

    <form @submit=${onSubmit} id="edit" action="#" method="">
        <fieldset>
            <p class="field title">
                <label for="title">Title:</label>
                <input type="text" name="title" id="title" placeholder="Enter article title" .value=${item.title}>
            </p>

            <p class="field category">
                <label for="category">Category:</label>
                <input type="text" name="category" id="category" placeholder="Enter article category" .value=${item.category}>
            </p>
            <p class="field">
                <label for="content">Content:</label>
                <textarea name="content" id="content" .innerText=${item.content}></textarea>
            </p>

            <p class="field submit">
                <input class="btn submit" type="submit" value="Save Changes">
            </p>

        </fieldset>
    </form>
</section>
`

export async function editPage(ctx) {
    const id = ctx.params.id;
    const item = await getItemById(id);
    ctx.render(editTemplate(item, onSubmit));

    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const title = formData.get('title');
        const category = formData.get('category').trim();
        const content = formData.get('content');

        if (!title || !category || !content) {
            return alert('All fields are required!');
        }

        if (category != 'JavaScript' && category != 'C#' && category != 'Java' && category != 'Python') {
            return alert('Invalid category!');
        }

        await editArticle(item._id, { title, category, content });
        ctx.page.redirect(`/details/${item._id}`);

    }
}