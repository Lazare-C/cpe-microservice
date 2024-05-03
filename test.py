import requests

def delete_card(card_id):
    """Deletes a card with the given ID using the provided API.

    Args:
        card_id (int): The ID of the card to delete.

    Returns:
        requests.Response: The response object from the DELETE request.

    Raises:
        requests.exceptions.RequestException: If an error occurs during the request.
        ValueError: If the card ID is not a positive integer.
    """

    if not isinstance(card_id, int) or card_id <= 0:
        raise ValueError("Invalid card ID: Must be a positive integer.")

    url = f"http://tp.cpe.fr:8083/card/{card_id}"

    try:
        response = requests.delete(url)
        response.raise_for_status()  # Raise an exception for non-2xx status codes
        print(f"Card ID {card_id} deleted successfully.")
    except requests.exceptions.RequestException as e:
        print(f"Error deleting card ID {card_id}: {e}")
    except requests.exceptions.HTTPError as e:
        print(f"API error deleting card ID {card_id}: {e}")

if __name__ == "__main__":
    # Example usage (replace with your desired range)
    for card_id in range(1, 110):  # Modify the range as needed
        delete_card(card_id)
