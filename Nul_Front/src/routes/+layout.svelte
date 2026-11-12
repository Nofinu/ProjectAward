<script lang="ts">
    import { goto } from '$app/navigation';
	import favicon from '$lib/assets/favicon.svg';
    import { onMount } from 'svelte';

	let { children,data } = $props();

</script>

<svelte:head>
	<link rel="icon" href={favicon} />
</svelte:head>

<nav>
	<div>
		<a href="http://localhost:5173" class="button">Nuls 2026</a>
		<a href="http://localhost:5173/ShowPage" class="button">ShowPage</a>
	</div>
	<div>
	{#if data.username}
		<a href="http://localhost:5173/LogOut" class="button" data-sveltekit-reload>logout</a>
	{/if}
	{#if data.username}
		<a href="http://localhost:5173/" class="button" data-sveltekit-reload>{data.username}</a>

	{:else}
		<a href="https://id.twitch.tv/oauth2/authorize?response_type=code&client_id=pre477msnx5x2s4j08lwznegnnnipr&redirect_uri=http://localhost:5173/Authorization&scope=user%3Aread%3Aemail&state=c3ab8aa609ea11e793ae92361f002671" class="button twitch">Connexion via Twitch</a>
	{/if}
	</div>

</nav>


{@render children()}

<style>
	nav {
		display: flex;
		flex-direction: row;
		justify-content:space-between;
		align-items: right;
	}

	.button {
		font: bold 16px Arial;
		text-decoration: none;
		background-color: #EEEEEE;
		color: #333333;
		padding: 5px 9px 5px 9px;
		border-radius: 5px;
	}

	.twitch{
		background-color: #9146FF;
		box-shadow: 0px 2px  #7438CC;
		color: #FFFFFF;
	}
	.twitch:hover{
		transform: translateY(-3px);
		box-shadow: 0px 5px  #7438CC;
	}
</style>