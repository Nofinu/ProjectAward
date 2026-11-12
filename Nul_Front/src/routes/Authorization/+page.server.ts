import { CLIENT_ID, CLIENT_SECRET, TWITCH_REDIRECT_URI } from '$env/static/private';
import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async ({ url, fetch, cookies }) => {
  const code = url.searchParams.get('code');

  if (!code) {
    return { erreur: "Aucun code dans l'URL" };
  }

  console.log(CLIENT_ID)

  // 1. Échange du code contre un token
  const res = await fetch('https://id.twitch.tv/oauth2/token', {
    method: 'POST',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    body: new URLSearchParams({
      client_id: CLIENT_ID,
      client_secret: CLIENT_SECRET,
      code,
      grant_type: 'authorization_code',
      redirect_uri: TWITCH_REDIRECT_URI
    })
  });

  if (!res.ok) {
    console.log('Erreur Twitch :', res.status, await res.text());
    return { erreur: "Échec de l'échange du code" };
  }

  const token = await res.json(); // une seule lecture

  cookies.set('twitch_token', token.access_token, {
    path: '/',
    httpOnly: true,
    sameSite: 'lax',
    maxAge: token.expires_in
  });

  // 2. Récupération du nom d'utilisateur
  const userRes = await fetch('https://api.twitch.tv/helix/users', {
    headers: {
      Authorization: `Bearer ${token.access_token}`,
      'Client-Id': CLIENT_ID
    }
  });

  if (userRes.ok) {
    const user = await userRes.json();
    cookies.set('twitch_username', user.data[0].display_name, {
      path: '/',
      sameSite: 'lax',
      maxAge: token.expires_in
    });
  }

  redirect(303, '/'); // en dehors de tout try/catch
};