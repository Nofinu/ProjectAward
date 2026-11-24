<script lang="ts">
	import favicon from '$lib/assets/favicon.svg';
	import { afterNavigate } from '$app/navigation';

	let { children, data } = $props();

	let menuOuvert = $state(false);

	const mois = [
		'Decembre', 'Janvier', 'Fevrier', 'Mars', 'Avril', 'Mai',
		'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre'
	];

	const libelles: Record<string, string> = { Fevrier: 'Février' };

	// Referme le menu après chaque changement de page
	afterNavigate(() => {
		menuOuvert = false;
	});
</script>

<svelte:head>
	<link rel="icon" href={favicon} />
</svelte:head>

<svelte:window onkeydown={(e) => e.key === 'Escape' && (menuOuvert = false)} />

<nav>
	<a href="/" class="button">Nuls 2026</a>

	<button
		type="button"
		class="burger"
		class:ouvert={menuOuvert}
		aria-label="Menu"
		aria-expanded={menuOuvert}
		aria-controls="menu"
		onclick={() => (menuOuvert = !menuOuvert)}
	>
		<span></span>
		<span></span>
		<span></span>
	</button>

	<div class="menu" id="menu" class:open={menuOuvert}>
		<div class="links">
			{#each mois as m}
				<a href="/Vote/{m}" class="button">{libelles[m] ?? m}</a>
			{/each}
		</div>

		<div class="auth">
			{#if data.username}
				<a href="https://nulawards.duckdns.org/LogOut" class="button" data-sveltekit-reload>logout</a>
				<a href="https://nulawards.duckdns.org/" class="button" data-sveltekit-reload>{data.username}</a>
			{:else}
				<a href="https://id.twitch.tv/oauth2/authorize?response_type=code&client_id=pre477msnx5x2s4j08lwznegnnnipr&redirect_uri=https://nulawards.duckdns.org/Authorization&scope=user%3Aread%3Aemail&state=c3ab8aa609ea11e793ae92361f002671" class="button twitch">Connexion via Twitch</a>
			{/if}
		</div>
	</div>
</nav>

{@render children()}

<style>
	:global(html) {
		min-height: 100%;
	}

	:global(body) {
		margin: 0;
		min-height: 100vh;
		background: #0A0F11;
		background: linear-gradient(153deg, rgba(10, 15, 17, 1) 0%, rgba(15, 35, 46, 1) 68%, rgba(2, 128, 163, 1) 100%);
		background-attachment: fixed;
		color: white;
		font-family: system-ui, sans-serif;
	}

	nav {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		flex-wrap: wrap;
		gap: 8px;
		padding: 8px;
	}

	/* Desktop : le wrapper disparait, les 2 blocs se placent directement dans la nav */
	.menu {
		display: contents;
	}

	.links,
	.auth {
		display: flex;
		flex-wrap: wrap;
		align-items: center;
		gap: 8px;
	}

	.button {
		font: bold 16px Arial;
		text-decoration: none;
		background-color: #EEEEEE;
		color: #333333;
		padding: 5px 9px;
		border-radius: 5px;
		white-space: nowrap;
	}

	.twitch {
		background-color: #9146FF;
		box-shadow: 0px 2px #7438CC;
		color: #FFFFFF;
	}

	.twitch:hover {
		transform: translateY(-3px);
		box-shadow: 0px 5px #7438CC;
	}

	/* Bouton burger : caché sur desktop */
	.burger {
		display: none;
		flex-direction: column;
		justify-content: center;
		gap: 5px;
		width: 44px;
		height: 44px;
		padding: 10px;
		box-sizing: border-box;
		background: #EEEEEE;
		border: none;
		border-radius: 5px;
		cursor: pointer;
	}

	.burger span {
		display: block;
		height: 3px;
		width: 100%;
		background: #333333;
		border-radius: 2px;
		transition: transform 0.2s, opacity 0.2s;
	}

	/* Animation en croix quand le menu est ouvert */
	.burger.ouvert span:nth-child(1) {
		transform: translateY(8px) rotate(45deg);
	}
	.burger.ouvert span:nth-child(2) {
		opacity: 0;
	}
	.burger.ouvert span:nth-child(3) {
		transform: translateY(-8px) rotate(-45deg);
	}

	/* Tablette + mobile : menu burger */
	@media (max-width: 1250px) {
		.burger {
			display: flex;
		}

		.menu {
			display: none;
			flex-direction: column;
			flex-basis: 100%;
			gap: 12px;
		}

		.menu.open {
			display: flex;
		}

		.links {
			display: grid;
			grid-template-columns: repeat(2, 1fr);
		}

		.auth {
			flex-direction: column;
			align-items: stretch;
		}

		.button {
			font-size: 15px;
			padding: 10px 14px; /* zone tactile plus grande */
			text-align: center;
		}

		.twitch:hover {
			transform: none;
			box-shadow: 0px 2px #7438CC;
		}
	}
</style>