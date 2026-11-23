<script lang="ts">
    import { enhance } from '$app/forms';

    let { form } = $props();

    const month = 1;
    const games = [1, 2, 3, 4, 5, 6];
</script>

<h1>Page where to show Games</h1>

{#if form?.erreur}
    <p class="erreur">{form.erreur}</p>
{:else if form?.succes}
    <p class="succes">Vote enregistré pour le jeu {form.game} !</p>
{/if}

<div class="grid">
    {#each games as id}
        <div class="game">
            <h2>Game {id}</h2>
            <p>Description</p>

            <form method="POST" action="?/vote" use:enhance>
                <input type="hidden" name="game" value={id} />
                <input type="hidden" name="month" value={month} />
                <button>Vote game</button>
            </form>
        </div>
    {/each}
</div>

<style>
    .grid {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
        gap: 16px;
        padding: 8px;
    }

    .game {
        width: 100%;
        box-sizing: border-box;
        padding: 12px;
        border: 1px solid #DDDDDD;
        border-radius: 8px;
    }

    .game button {
        font: bold 16px Arial;
        padding: 8px 14px;
        border-radius: 5px;
        cursor: pointer;
    }

    .erreur { color: crimson; }
    .succes { color: seagreen; }

    /* Mobile */
    @media (max-width: 600px) {
        h1 {
            font-size: 1.5rem;
            padding: 0 8px;
        }

        .grid {
            grid-template-columns: 1fr; /* une seule colonne */
        }

        .game button {
            width: 100%;
            padding: 12px; /* zone tactile plus grande */
        }
    }
</style>