import { html } from '../../node_modules/lit-html/lit-html.js';
import { getItemById, deleteRecord } from '../api/data.js';

const detailsTemplate = (item, isOwner, onDelete) => html`
<div class="container home some">
    <img class="det-img" src=${item.imageUrl}/>
    <div class="desc">
        <h2 class="display-5">${item.title}e</h2>
        <p class="infoType">Description:</p>
        <p class="idea-description">${item.description}</p>
    </div>
    ${isOwner ? html` <div class="text-center">
        <a class="btn detb" @click=${onDelete} href="javascript:void(0)">Delete</a>
    </div>` : ''}
</div>
`

export async function detailsPage(ctx) {
    const id = ctx.params.id;
    const item = await getItemById(id);
    const userId = sessionStorage.getItem('userId');

    ctx.render(detailsTemplate(item, item._ownerId === userId, onDelete));

    async function onDelete() {
        const confirmed = confirm('Are you sure you want to delete this item?');
        if (confirmed) {
            deleteRecord(item._id);
            ctx.page.redirect('/catalog');
        }
    }
}