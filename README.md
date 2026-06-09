# Social_media_App
L'application a les fonctionalités suivantes:
    1- Connection a un compte
    2- Créer un compte
    3- Publier un poste (texte seulement)
    4- Conversation privée avec des amis (utlisant ajax)
    5- Changer de photo de profile

L'application utilise une base de donnée locale (MSSQL). Les scripts pour la creation des tables et de les peuplés sont dans resources.sql

Les photos sont enregistrés à l'exterieur du projet dans un fichier appelé SocialMediaUploads. Lorsqu'on lance le projet pour la première fois, il n'a pas de photo.

La fonctionalités suivante n'est pas encore ajoutés. Quoique c'est un peu trop tard, je prévois les rajoutés pendant les vacances:
    1- Envoyer une requête d'amis
    2- Accepter/refuser une requête
    3- Montrer la photo de profile comme avatar
Pour le moment la seule façon d'ajouter quelqu'un comme amis est directement en faisant des INSERT dans la table friendships

P-S: Je n'ai pas porter grosse attention à implementer bootstrap. Une erreur de ma part.
