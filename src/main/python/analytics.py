"""Analytics module for tech store sales reporting."""

from dataclasses import dataclass
from typing import Dict, List


@dataclass
class SaleRecord:
    """Represents a single sale transaction."""

    product_id: str
    quantity: int
    unit_price: float

    @property
    def total(self) -> float:
        """Calculate the total value of this sale."""
        return self.quantity * self.unit_price


def calculate_revenue(sales: List[SaleRecord]) -> float:
    """
    Calculate total revenue from a list of sales.

    Args:
        sales: List of sale records to aggregate.

    Returns:
        Total revenue as a float.
    """
    return sum(sale.total for sale in sales)


def group_by_product(sales: List[SaleRecord]) -> Dict[str, float]:
    """
    Group sales revenue by product ID.

    Args:
        sales: List of sale records to process.

    Returns:
        Dictionary mapping product ID to total revenue.
    """
    result: Dict[str, float] = {}
    for sale in sales:
        result[sale.product_id] = result.get(sale.product_id, 0.0) + sale.total
    return result


# VIOLATION: camelCase function name â€” must be snake_case
# VIOLATION: no docstring
# VIOLATION: no type hints on parameters or return value
def calculateProductSummary(sales, productId):
    total = 0
    names = ""
    # VIOLATION: range(len(...)) instead of direct iteration or enumerate
    for i in range(len(sales)):
        if sales[i].product_id == productId:
            total = total + sales[i].total
            # VIOLATION: string concatenation in a loop (use join())
            names = names + sales[i].product_id + ", "
    return total


# VIOLATION: 8 parameters â€” far above the 3-4 limit
# VIOLATION: single-letter / non-descriptive parameter names (s, d, r, t, n, m)
# VIOLATION: no docstring
# VIOLATION: no type hints
def process(s, d, r, t, n, m, extra_param_1, extra_param_2):
    try:
        result = s * d / r
        if result > t:
            result = t
    except:  # VIOLATION: bare except â€” must catch specific exception type
        result = 0
    return result